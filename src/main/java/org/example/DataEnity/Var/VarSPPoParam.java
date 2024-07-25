package org.example.DataEnity.Var;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VarSPPoParam implements Serializable {
    private String szParamName;
    private String szParamValue;
    private long szParamDesc;
    private long varParamType;
    private long varParamUsage;
}
