package by.laguta.skryaga.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
//@Entity
//@Table(name = "users")
public class User {

    private Long id;
    private String name;
    private String email;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    void preSave() {
        if(createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    void preUpdate() {
        if(updatedAt == null) {
            updatedAt = LocalDateTime.now();
        }
    }
}
