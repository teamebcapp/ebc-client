package teamebcapp.ebc.Frag1;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import teamebcapp.ebc.BusinessCard.BusinessCard;
import teamebcapp.ebc.BusinessCard.BusinessCardService;
import teamebcapp.ebc.InfoUser;
import teamebcapp.ebc.R;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.ItemViewHolder> {

    private ArrayList<MyItem> mItems = new ArrayList<>();
    private Context context;
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    // 직전에 클릭됐던 Item의 position
    private int prePosition = -1;
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_item, parent, false);
        return new ItemViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(mItems.get(position),position);
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return mItems.size();
    }

    /* 아이템 데이터 추가를 위한 함수. 자신이 원하는대로 작성 */
    public void addItem(int OwnerBcSeq, int OwnerSeq, int BcSeq, String UserId, String Name, String Company, String Position, String Duty, String Phone, String Email,
                        String Depart, String Team, String Tel, String Fax, String Address) {
        MyItem addInfo;
        addInfo = new MyItem();
        addInfo.setOwnerBcSeq(OwnerBcSeq);
        addInfo.setOwnerSeq(OwnerSeq);
        addInfo.setBcSeq(BcSeq);
        addInfo.setUserId(UserId);
        addInfo.setName(Name);
        addInfo.setCompany(Company);
        addInfo.setPosition(Position);
        addInfo.setDuty(Duty);
        addInfo.setPhone(Phone);
        addInfo.setEmail(Email);
        addInfo.setDepart(Depart);
        addInfo.setTeam(Team);
        addInfo.setTel(Tel);
        addInfo.setFax(Fax);
        addInfo.setAddress(Address);

        mItems.add(addInfo);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nameText;
        private TextView posiText;
        private TextView comText;
        private TextView phoneText;
        private TextView teamText;
        private Button editButton;
        private Button deleteButton;
        private MyItem myItem;
        private int position;
        private LinearLayout linearView;
        private int ownerseq;

        ItemViewHolder(View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.itemnameText);
            posiText = itemView.findViewById(R.id.itemposiText);
            comText = itemView.findViewById(R.id.itemcomText);
            phoneText = itemView.findViewById(R.id.itemphoneText);
            teamText = itemView.findViewById(R.id.itemteamText);
            linearView=itemView.findViewById(R.id.linearView);
            editButton=itemView.findViewById(R.id.editButton);
            deleteButton=itemView.findViewById(R.id.deleteButton);

        }

        void onBind(MyItem myItem,int position) {
            this.myItem=myItem;
            this.position=position;

            changeVisibility(selectedItems.get(position));

            nameText.setText(myItem.getName());
            comText.setText(myItem.getCompany());
            posiText.setText(myItem.getPosition());
            phoneText.setText(myItem.getPhone());
            teamText.setText(myItem.getTeam());
;           ownerseq=myItem.getOwnerSeq();

            //unfold scroll
            itemView.setOnClickListener(this);
/*            nameText.setOnClickListener(this);
            comText.setOnClickListener(this);
            posiText.setOnClickListener(this);
            phoneText.setOnClickListener(this);
            teamText.setOnClickListener(this);*/

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.linearitem:
                    if (selectedItems.get(position)) {
                        // 펼쳐진 Item을 클릭 시
                        selectedItems.delete(position);
                        //수정버튼 클릭시
                        /*editButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                        //삭제버튼 클릭시
                        deleteButton.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                Toast.makeText(context, ownerseq, Toast.LENGTH_SHORT).show();
                                BusinessCard OwnerSeq=new BusinessCard(ownerseq);
                                BusinessCardService deleteBusinessCardService = teamebcapp.ebc.Retrofit.retrofit.create(BusinessCardService.class);
                                Call<BusinessCard> deleteCall = deleteBusinessCardService.DeleteBC(ownerseq,InfoUser.access_token);
                                deleteCall.enqueue(new Callback<BusinessCard>() {
                                    @Override
                                    public void onResponse(Call<BusinessCard> call, Response<BusinessCard> response) {

                                        try { if (response.isSuccessful() == true) {
                                            Toast.makeText(context, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                                        }
                                        } catch (Exception e) {
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<BusinessCard> call, Throwable t) {
                                        call.cancel();
                                    }
                                });
                            }
                        });*/
                    } else {
                        // 직전의 클릭됐던 Item의 클릭상태를 지움
                        selectedItems.delete(prePosition);
                        // 클릭한 Item의 position을 저장
                        selectedItems.put(position, true);
                    }
                    // 해당 포지션의 변화를 알림
                    if (prePosition != -1) notifyItemChanged(prePosition);
                    notifyItemChanged(position);
                    // 클릭된 position 저장
                    prePosition = position;
                    break;
            }
        }

        /**
         * 클릭된 Item의 상태 변경
         * @param isExpanded Item을 펼칠 것인지 여부
         */
        private void changeVisibility(final boolean isExpanded) {
            // height 값을 dp로 지정해서 넣고싶으면 아래 소스를 이용
            int dpValue = 100;
            float d = context.getResources().getDisplayMetrics().density;
            int height = (int) (dpValue * d);

            // ValueAnimator.ofInt(int... values)는 View가 변할 값을 지정, 인자는 int 배열
            ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, height) : ValueAnimator.ofInt(height, 0);
            // Animation이 실행되는 시간, n/1000초
            va.setDuration(300);
            va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    // value는 height 값
                    int value = (int) animation.getAnimatedValue();

                    linearView.getLayoutParams().height = value;
                    linearView.requestLayout();
                    // imageView가 실제로 사라지게하는 부분
                    linearView.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
                }
            });
            // Animation start
            va.start();
        }

    }
}
