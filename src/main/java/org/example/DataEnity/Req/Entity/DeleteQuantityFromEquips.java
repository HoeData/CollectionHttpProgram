package org.example.DataEnity.Req.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteQuantityFromEquips implements Serializable {
    private String szDAQSUUID;
    private String szDAQEquipID;
    private String szDAQQID;
}
