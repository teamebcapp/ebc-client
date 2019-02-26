package teamebcapp.ebc.Frag1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import teamebcapp.ebc.BusinessCard.BusinessCard;
import teamebcapp.ebc.BusinessCard.BusinessCardService;
import teamebcapp.ebc.InfoUser;
import teamebcapp.ebc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {
    private int frag1BcSeq;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private ListView mListView;
    private MyAdapter myAdapter;


    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment1, null);

        myAdapter = new MyAdapter();
        mListView = view.findViewById(R.id.listView);

        getListBCs();
/*

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BusinessCardService getbusinessCardService = teamebcapp.ebc.Retrofit.retrofit.create(BusinessCardService.class);
                Call<BusinessCard> getcall = getbusinessCardService.GetonlyBC();
                BusinessCardService deletebusinessCardService = teamebcapp.ebc.Retrofit.retrofit.create(BusinessCardService.class);
                Call<BusinessCard> deletecall = deletebusinessCardService.DeleteBC();

                deletecall.enqueue(new Callback<BusinessCard>() {
                    @Override
                    public void onResponse(Call<BusinessCard> call, Response<BusinessCard> response) {

                        try {
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<BusinessCard> call, Throwable t) {
                        call.cancel();
                    }
                });
            }
        });
*/

        return view;
    }


    private void getListBCs() {
        BusinessCardService businessCardService = teamebcapp.ebc.Retrofit.retrofit.create(BusinessCardService.class);
        Call<List<BusinessCard>> call = businessCardService.GetBCs(InfoUser.transuserID, InfoUser.access_token);

        call.enqueue(new Callback<List<BusinessCard>>() {
            @Override
            public void onResponse(Call<List<BusinessCard>> call, Response<List<BusinessCard>> response) {

                try {
                    List<BusinessCard> result = response.body();

                    int i = 0, resultSize = result.size();
                    while (resultSize > 0) {
                        myAdapter.addItem(result.get(i).BcSeq, result.get(i).UserId, result.get(i).Name, result.get(i).Company,
                                result.get(i).Position, result.get(i).Duty, result.get(i).Phone, result.get(i).Email, result.get(i).Depart,
                                result.get(i).Team, result.get(i).Tel, result.get(i).Fax, result.get(i).Address);
                        mListView.setAdapter(myAdapter);
                        resultSize--;
                        i++;
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<List<BusinessCard>> call, Throwable t) {
                call.cancel();
            }
        });
    }
}