package org.example.DataEnity.Var;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VarSCommPluginParamP implements Serializable {
    private List<String> seqAllParam;
    private String szLibFileName;
    private String szLibName;
    private int varPos;
    private int varType;
}
