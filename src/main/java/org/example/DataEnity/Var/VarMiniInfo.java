package org.example.DataEnity.Var;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VarMiniInfo implements Serializable {
    private boolean bEnable;
    private String szDAQSUUID;
    private String szDAQEName;
    private String szDAQEquipID;
    private String varLastState;
    private long llastStateTime;
    private String szDesc;
}
