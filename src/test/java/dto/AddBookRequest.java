package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AddBookRequest {

    String userId;
    List<Isbn> collectionOfIsbns;
}
