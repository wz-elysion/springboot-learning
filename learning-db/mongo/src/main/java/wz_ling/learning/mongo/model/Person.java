package wz_ling.learning.mongo.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "person")
public class Person {

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("age")
    private int age;
}