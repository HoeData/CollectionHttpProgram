package org.example.DataEnity.Req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.example.DataEnity.Var.VarDAQSParam;
import org.example.DataEnity.Var.VarDAQStationMiniInfo;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddDaqStationRequest implements Serializable {
    private VarDAQStationMiniInfo varDAQStationMiniInfo;
    private VarDAQSParam varDAQSParam;

    public boolean IsEmptyHttpAddDaqStationRequest() {
        return StringUtils.isEmpty(this.varDAQStationMiniInfo.getSzStationName());
    }

}
