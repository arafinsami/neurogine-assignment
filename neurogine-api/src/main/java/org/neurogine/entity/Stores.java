package org.neurogine.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serial;
import java.io.Serializable;

@Data
@Document("app_stores")
public class Stores implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @Field("stores_name")
    private String name;

    @Field("stores_description")
    private String description;

    @Indexed(unique = true)
    @Field("stores_email")
    private String email;

    @Field(name = "stores_image")
    private String image;

    @Field(name = "stores_category")
    private String category;

    @Field(name = "stores_address")
    private String address;

    @Field(name = "stores_geolocation")
    private String geolocation;
}
