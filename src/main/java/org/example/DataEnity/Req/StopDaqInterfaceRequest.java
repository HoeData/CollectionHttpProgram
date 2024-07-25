package org.example.DataEnity.Req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StopDaqInterfaceRequest implements Serializable {
    private String szDAQSUUID;

    public boolean IsEmptyHttpStopDaqInterfaceRequest() {
        return StringUtils.isEmpty(this.szDAQSUUID);
    }
}
