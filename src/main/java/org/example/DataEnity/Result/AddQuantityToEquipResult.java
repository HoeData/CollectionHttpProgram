package org.example.DataEnity.Result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddQuantityToEquipResult implements Serializable {
    private long AddRetl;
    private String szDAQQID;
}
