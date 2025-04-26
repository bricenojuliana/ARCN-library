package eci.arcn.library.inventory.infrastructure.api;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CopyDTO {
    private UUID bookId;
    private String barCode;
}
