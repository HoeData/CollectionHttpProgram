package org.example.DataEnity.Var;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.DataEnity.Seq.SeqSCommParam;
import org.example.DataEnity.Seq.SeqSPPOParam;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VarSParam implements Serializable {
    private String szSDAQSign;
    private String szUnit;
    private String szWrongValue;
    private int lWrongQuantity;
    private int sSDAQType;
    private String szSDesc;
    private List<SeqSCommParam> seqSCommParam;
    private List<SeqSPPOParam> seqSPPOParam;
}
