package org.example.DataEnity.Req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DelDaqStationRequest {
    private String szDAQSUUID;

    public boolean IsEmptyHttpDelDaqStationRequest() {
        return StringUtils.isEmpty(this.szDAQSUUID);
    }
}
