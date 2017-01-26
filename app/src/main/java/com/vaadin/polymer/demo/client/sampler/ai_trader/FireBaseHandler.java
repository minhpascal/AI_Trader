package com.vaadin.polymer.demo.client.sampler.ai_trader;
import com.github.mikephil.charting.data.CandleEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import pro.xstore.api.message.records.RateInfoRecord;

/**
 * Created by Piotr on 2016-12-02.
 * Provides interface to FireBase database by:
 * Saving data
 * Getting data as List<Object> based on various child String
 */

public class FireBaseHandler
{

    private static List<String> internalCopy= new ArrayList<>();

    static List<String> getInternalCopy() {

        return internalCopy;
    }

    private DatabaseReference databaseReference;

    FireBaseHandler() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
    }

    public static String EncodeString(String string) {
        return string.replace(".", ",").replace("[","+").replace("]","-");
    }

    public static String DecodeString(String string) {
        return string.replace(",", ".").replace("+","[").replace("-","]");
    }

    //this function can be moved outside this class
    List<CandleEntry> saveApiRecordsToCandleEntryList (List<RateInfoRecord> records, ChartRangeInfo chartRangeInfo)
    {
        List<CandleEntry> data = new LinkedList<>();
        for (int i = 0; i < records.size(); i++)
        {

            SymbolData symbolData = new SymbolData(
                    records.get(i).getCtm(),
                    records.get(i).getVol(),
                    chartRangeInfo

            );

            CandleEntry candleEntry = new CandleEntry(
                    i,
                    (float) (records.get(i).getHigh()+records.get(i).getOpen()),
                    (float) (records.get(i).getLow()+records.get(i).getOpen()),
                    (float)  records.get(i).getOpen(),
                    (float) (records.get(i).getClose()+records.get(i).getOpen())
                    ,symbolData

            );

            data.add(i,candleEntry);
        }
        return data;
    }

    void saveCandleListToFireBase(List<CandleEntry> data, ChartRangeInfo chartRangeInfo) {

        for (int i = 0; i < data.size(); i++) {
            databaseReference.child(EncodeString(chartRangeInfo.getSymbol())).child(EncodeString(chartRangeInfo.getPeriod().toString())).setValue(data);

        }
    }

    void getDataFromFireBaseDb(String childName)
    {
     databaseReference.child(childName).addValueEventListener(new ValueEventListener() {

         @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

             List<Objects> listObject = (List<Objects>) dataSnapshot.getValue();

                    for (int i=0;i<listObject.size();i++)
                    {
                        internalCopy.add(String.valueOf(listObject.get(i)));
                    }
                }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            //on cancel
            }
        });
     }
}

