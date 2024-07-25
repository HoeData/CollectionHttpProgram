package org.example.DataEnity.Seq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeqAllParam implements Serializable {
    private String szParamChName;
    private String szParamDesc;
    private String szParamName;
    private String szParamValue;
    private long varParamType;
    private long varParamUsage;
    private String vecOptionalValue;
}
