package org.example.DataEnity.Var;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VarDAQStationMiniInfo implements Serializable {
    private Boolean bAutoStart;
    private Boolean bProtectRun;
    private int iCurState;
    private int llastStateTime;
    private String szDesc;
    private String szStationName;
    private String szUUID;
    private int varLastState;
}
