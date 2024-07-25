package org.example.DataEnity.Seq;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeqSPPOParam implements Serializable {
    private String szParamName;
    private String szParamValue;
    private String szParamDesc;
    private String varParamType;
    private long varParamUsage;
}
