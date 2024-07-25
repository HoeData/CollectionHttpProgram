package org.example.DataEnity.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapDPPoAndCommPlugin implements Serializable {
    private String szDPPOPluginObjID;
    private List<String> seqDCommPluginObjID;
}
