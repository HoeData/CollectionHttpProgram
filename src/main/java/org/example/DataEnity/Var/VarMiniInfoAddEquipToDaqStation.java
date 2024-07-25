package org.example.DataEnity.Var;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VarMiniInfoAddEquipToDaqStation implements Serializable {
    private String szDAQSUUID;
    private String szDAQEName;
}
