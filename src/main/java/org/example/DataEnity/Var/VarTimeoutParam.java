package org.example.DataEnity.Var;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VarTimeoutParam implements Serializable {
    private long lSResetTimeout;
    private long lDResetTimeout;
    private long lScanInterval;
    private long lRecvDataTimeout;
    private long lBusRecvDataTimeout;
    private long iRetryTimes;
    private long iErrorTimes;
}
