package eci.arcn.library.inventory.infrastructure.copy;

import eci.arcn.library.inventory.domain.book.BookId;
import eci.arcn.library.inventory.domain.copy.BarCode;
import eci.arcn.library.inventory.domain.copy.Copy;
import eci.arcn.library.inventory.domain.copy.CopyId;
import eci.arcn.library.inventory.domain.copy.CopyRepository;
import eci.arcn.library.inventory.infrastructure.book.BookEntity;
import eci.arcn.library.inventory.infrastructure.book.BookEntityRepository;

import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class JpaCopyRepository implements CopyRepository {

    private final CopyEntityRepository copyEntityRepository;
    private final BookEntityRepository bookEntityRepository;

    public JpaCopyRepository(CopyEntityRepository copyEntityRepository, BookEntityRepository bookEntityRepository) {
        this.copyEntityRepository = copyEntityRepository;
        this.bookEntityRepository = bookEntityRepository;
    }

    @Override
    public Copy save(Copy copy) {
        BookEntity bookEntity = bookEntityRepository.findById(copy.getBookId().id())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        CopyEntity copyEntity = new CopyEntity(
                    copy.getId().id(),
                    copy.getBarCode().code(),
                    bookEntity,
                    copy.isAvailable()
            );

        copyEntityRepository.save(copyEntity);
        return copy;
    }

    @Override
    public Copy findById(CopyId id) {
        CopyEntity copyEntity = copyEntityRepository.findById(id.id())
                .orElseThrow(() -> new IllegalArgumentException("Copy not found"));
        return new Copy(new BookId(copyEntity.getBookEntity().getId()), new BarCode(copyEntity.getBarCode()));
    }

    @Override
    public void deleteById(CopyId id) {
        copyEntityRepository.deleteById(id.id());
    }

    @Override
    public Iterable<Copy> findAll() {
        Iterable<CopyEntity> copyEntities = copyEntityRepository.findAll();
        return StreamSupport.stream(copyEntities.spliterator(), false)
                .map(entity -> new Copy(new BookId(entity.getBookEntity().getId()), new BarCode(entity.getBarCode())))
                .collect(Collectors.toList());
    }

    @Override
    public Copy findByBookId(BookId bookId) {
        BookEntity bookEntity = bookEntityRepository.findById(bookId.id())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        CopyEntity copyEntity = copyEntityRepository.findByBookEntity(bookEntity)
                .orElseThrow(() -> new IllegalArgumentException("Copy not found"));
        return new Copy(new BookId(copyEntity.getBookEntity().getId()), new BarCode(copyEntity.getBarCode()));
    }


    @Override
    public Iterable<Copy> findByBookIdAndAvailable(BookId bookId, boolean available) {
        BookEntity bookEntity = bookEntityRepository.findById(bookId.id())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        Iterable<CopyEntity> copyEntities = copyEntityRepository.findByBookEntityAndAvailable(bookEntity, available);
        return StreamSupport.stream(copyEntities.spliterator(), false)
                .map(entity -> new Copy(new BookId(entity.getBookEntity().getId()), new BarCode(entity.getBarCode())))
                .collect(Collectors.toList());
    }
}
