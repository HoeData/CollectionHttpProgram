package org.example.DataEnity.Var;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VarMiniInfoEditQuantityToEquip implements Serializable {
    private String szDAQSUUID;
    private String szDAQEID;
    private String szDAQQID;
    private String szDAQQName;
    private String sRDSign;
}
