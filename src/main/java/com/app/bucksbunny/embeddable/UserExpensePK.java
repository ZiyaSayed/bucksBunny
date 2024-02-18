package com.app.bucksbunny.embeddable;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserExpensePK {

    @Column(name="user_id")
    private String userId;

    @Column(name="expense_id")
    private int expenseId;
}
