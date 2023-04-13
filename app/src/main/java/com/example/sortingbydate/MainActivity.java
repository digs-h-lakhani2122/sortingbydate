package com.example.sortingbydate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final List<PojoOfJsonArray> myOptions = new ArrayList<>();
    List<ListItem> consolidatedList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);

        myOptions.add(new PojoOfJsonArray("name 1", "10-04-2023"));
        myOptions.add(new PojoOfJsonArray("name 2", "04-04-2023"));//
        myOptions.add(new PojoOfJsonArray("name 2", "2016-06-05"));
        myOptions.add(new PojoOfJsonArray("name 3", "04-04-2023"));//
        myOptions.add(new PojoOfJsonArray("name 3", "2016-05-17"));
        myOptions.add(new PojoOfJsonArray("name 3", "04-04-2023"));//
        myOptions.add(new PojoOfJsonArray("name 3", "2016-05-17"));
        myOptions.add(new PojoOfJsonArray("name 2", "10-04-2023"));
        myOptions.add(new PojoOfJsonArray("name 3", "2016-05-17"));

        HashMap<String, List<PojoOfJsonArray>> groupedHashMap = groupDataIntoHashMap(myOptions);

        for (String date : groupedHashMap.keySet()) {
            DateItem dateItem = new DateItem();
            dateItem.setDate(date);
            consolidatedList.add(dateItem);


            for (PojoOfJsonArray pojoOfJsonArray : groupedHashMap.get(date)) {
                GeneralItem generalItem = new GeneralItem();
                generalItem.setPojoOfJsonArray(pojoOfJsonArray);//setBookingDataTabs(bookingDataTabs);
                consolidatedList.add(generalItem);
            }
        }


        Adapter adapter = new Adapter(this, consolidatedList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);


    }

    private HashMap<String, List<PojoOfJsonArray>> groupDataIntoHashMap(List<PojoOfJsonArray> listOfPojosOfJsonArray) {

        HashMap<String, List<PojoOfJsonArray>> groupedHashMap = new HashMap<>();

        for (PojoOfJsonArray pojoOfJsonArray : listOfPojosOfJsonArray) {

            String hashMapKey = pojoOfJsonArray.getDate();

            if (groupedHashMap.containsKey(hashMapKey)) {
                // The key is already in the HashMap; add the pojo object
                // against the existing key.
                groupedHashMap.get(hashMapKey).add(pojoOfJsonArray);
            } else {
                // The key is not there in the HashMap; create a new key-value pair
                List<PojoOfJsonArray> list = new ArrayList<>();
                list.add(pojoOfJsonArray);
                groupedHashMap.put(hashMapKey, list);
            }
        }


        return groupedHashMap;
    }
}