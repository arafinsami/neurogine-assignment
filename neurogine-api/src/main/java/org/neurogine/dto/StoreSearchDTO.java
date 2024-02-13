package org.neurogine.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neurogine.entity.Stores;

@Data
@NoArgsConstructor
public class StoreSearchDTO {

    private String id;

    private String name;

    private String description;

    private String email;

    private String image;

    private String category;

    private String address;

    private String geolocation;

    public static StoreSearchDTO from(Stores stores) {
        StoreSearchDTO dto = new StoreSearchDTO();
        dto.setId(stores.getId());
        dto.setName(stores.getName());
        dto.setDescription(stores.getDescription());
        dto.setEmail(stores.getEmail());
        dto.setImage(stores.getImage());
        dto.setCategory(stores.getCategory());
        dto.setAddress(stores.getAddress());
        dto.setGeolocation(stores.getGeolocation());
        return dto;
    }

    public Stores to() {
        Stores stores = new Stores();
        stores.setName(name);
        stores.setDescription(description);
        stores.setEmail(email);
        stores.setImage(image);
        stores.setCategory(category);
        stores.setAddress(address);
        stores.setGeolocation(getGeolocation());
        return stores;
    }

    public Stores update(Stores stores) {
        stores.setName(name);
        stores.setDescription(description);
        stores.setEmail(email);
        stores.setImage(image);
        stores.setCategory(category);
        stores.setAddress(address);
        stores.setGeolocation(getGeolocation());
        return stores;
    }
}
