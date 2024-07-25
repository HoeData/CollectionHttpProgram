package org.example.Interface;


import org.example.DataEnity.CollectionConfiguration;
import org.example.DataEnity.CollectionData;

public interface CollectSerialized {
    CollectionData ReadCollectionPointDatas(String var1);

    CollectionConfiguration ReadCollectionConfigurationDatas(String var1);

    void WriteCollectionConfigurationDatas(String var1, CollectionConfiguration var2);

    void WriteCollectionPointDatas(String var1, CollectionData var2);
}
