package org.example.DataEnity.Var;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VarDAQSParam implements Serializable {
    private boolean bEnableBackComm;
    private boolean bEnableBuffer;
    private int iMessageProcessThreadNum;
    private VarBufferParam varBufferParam;
    private int varDAQType=1;
    private int varPushMode;
    private VarPushParam varPushParam;
    private VarSCommPluginParamB varSCommPluginParamB;
    private VarSCommPluginParamP varSCommPluginParamP;
    private VarSPPoParam varSPPoParam;
    private VarTimeoutParam varTimeoutParam;
}
