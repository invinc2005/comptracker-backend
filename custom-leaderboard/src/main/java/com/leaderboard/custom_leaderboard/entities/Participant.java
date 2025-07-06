package com.leaderboard.custom_leaderboard.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.aspectj.bridge.IMessage;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Min(value = 0, message = "Score must be non-negative")
    @Max(value = 100, message = "Score cannot be greater than 100")
    private int score;

    @ManyToOne
    @JoinColumn(name = "competition_id", nullable = false)
    private Competition competition;
}
