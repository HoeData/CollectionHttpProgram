package org.example.DataEnity.Req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.example.DataEnity.Var.VarPluginParam;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddOnePlugObjRequest implements Serializable {
    private String szPluginObjID;
    private String szPluginObjName;
    private VarPluginParam varPluginParam;

    public boolean IsEmptyHttpAddOnePlugObjRequest() {
        return StringUtils.isEmpty(this.szPluginObjName) || StringUtils.isEmpty(this.varPluginParam.getSzLibFileName()) || this.varPluginParam.getSeqAllParam() != null;
    }
}
