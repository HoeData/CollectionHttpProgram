package org.example.DataEnity.Seq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeqDParam implements Serializable {
    private String szPPoPluginObjID;
    private String szDDAQSign;
    private int sDDAQType;
    private double dUpThreshold;
    private double dUpperLimit;
    private double dLowerLimit;
    private double dRatio;
    private double szDDesc;
    private double lWriteQuantiy;
    private List<SeqQParam> seqQParam;
    private List<SeqCommParam> seqCommParam;

}
