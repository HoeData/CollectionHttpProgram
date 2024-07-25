package org.example.DataEnity.Result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.DataEnity.Var.VarPluginParam;

import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllPlugObjResult implements Serializable {
    private String szDesc;
    private String szPluginObjID;
    private String szPluginObjName;
    private String szLibFileName;
    private String szLibName;
    private long varPos;
    private long varType;
    private List<VarPluginParam> varPluginParam;

}
