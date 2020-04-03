package teamebcapp.ebc.Frag1;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import teamebcapp.ebc.BusinessCard.BusinessCardService;
import teamebcapp.ebc.UserInfo;
import teamebcapp.ebc.R;
import teamebcapp.ebc.common.global.varForDeleteOtherBC;

public class OtherBCAdapter extends RecyclerView.Adapter<OtherBCAdapter.ItemViewHolder> {

    private ArrayList<MyItem> mItems = new ArrayList<>();
    private Context context;
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    private SparseBooleanArray selectedItemsedit = new SparseBooleanArray();
    String getName, getCom, getPhone, getPosi, getDuty;

    // 직전에 클릭됐던 Item의 position
    private int prePosition = -1;
    private int prePostionedit = -1;

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
        holder.onBind(mItems.get(position), position);
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return mItems.size();
    }

    /* 아이템 데이터 추가를 위한 함수.*/
    public void addItem(int OwnerSeq, int OwnerBcSeq, int BcSeq, String UserId, String Name, String Company, String Position, String Duty, String Phone, String Email,
                        String Depart, String Team, String Tel, String Fax, String Address) {
        MyItem OtherBCInfo;
        OtherBCInfo = new MyItem();
        OtherBCInfo.setOwnerBcSeq(OwnerBcSeq);
        OtherBCInfo.setOwnerSeq(OwnerSeq);
        OtherBCInfo.setBcSeq(BcSeq);
        OtherBCInfo.setUserId(UserId);
        OtherBCInfo.setName(Name);
        OtherBCInfo.setCompany(Company);
        OtherBCInfo.setPosition(Position);
        OtherBCInfo.setDuty(Duty);
        OtherBCInfo.setPhone(Phone);
        OtherBCInfo.setEmail(Email);
        OtherBCInfo.setDepart(Depart);
        OtherBCInfo.setTeam(Team);
        OtherBCInfo.setTel(Tel);
        OtherBCInfo.setFax(Fax);
        OtherBCInfo.setAddress(Address);

        mItems.add(OtherBCInfo);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nameText;
        private TextView posiText;
        private TextView comText;
        private TextView phoneText;
        private TextView teamText;
        TextView tv_nameText;
        TextView tv_position;
        TextView tv_phone;
        TextView tv_duty;
        TextView tv_company;
        private Button getButton;
        private Button deleteButton;
        private MyItem myItem;
        private int position;
        private LinearLayout linearView;
        private LinearLayout getLayout;

        ItemViewHolder(View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.itemnameText);
            comText = itemView.findViewById(R.id.itemcomText);
            phoneText = itemView.findViewById(R.id.itemphoneText);

            tv_position = itemView.findViewById(R.id.tv_detail_postion);
            tv_nameText = itemView.findViewById(R.id.tv_detail_name);
            tv_company = itemView.findViewById(R.id.tv_detail_company);
            tv_duty = itemView.findViewById(R.id.tv_detail_duty);
            tv_phone = itemView.findViewById(R.id.getphoneText);

            linearView = itemView.findViewById(R.id.linearView);
            getButton = itemView.findViewById(R.id.getDetailOtherBCButton);
            deleteButton = itemView.findViewById(R.id.deleteOtherBCButton);
            getLayout = itemView.findViewById(R.id.ly_detail);

        }

        void onBind(MyItem myItem, int position) {
            this.myItem = myItem;
            this.position = position;

            changeVisibility(selectedItems.get(position));
            changeVisibilityedit(selectedItemsedit.get(position));

            nameText.setText(myItem.getName());
            comText.setText(myItem.getCompany());
            phoneText.setText(myItem.getPhone());

            tv_position.setText(myItem.getPosition());
            tv_duty.setText(myItem.getTeam());
            tv_nameText.setText(myItem.getName());
            tv_company.setText(myItem.getCompany());
            tv_phone.setText(myItem.getPhone());

            itemView.setOnClickListener(this);
            getButton.setOnClickListener(this);
            deleteButton.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.linearitem:
                    if (selectedItems.get(position)) {
                        // 펼쳐진 Item을 클릭 시
                        selectedItems.delete(position);
                        selectedItemsedit.delete(position);

                    } else {
                        // 직전의 클릭됐던 Item의 클릭상태를 지움
                        selectedItems.delete(prePosition);
                        selectedItemsedit.delete(prePosition);
                        // 클릭한 Item의 position을 저장
                        selectedItems.put(position, true);
                    }
                    // 해당 포지션의 변화를 알림
                    if (prePosition != -1) notifyItemChanged(prePosition);
                    notifyItemChanged(position);
                    // 클릭된 position 저장
                    prePosition = position;
                    break;
                case R.id.getDetailOtherBCButton:

                    if (selectedItemsedit.get(position)) {
                        // 펼쳐진 Item을 클릭 시
                        selectedItemsedit.delete(position);

                    } else {
                        // 직전의 클릭됐던 Item의 클릭상태를 지움
                        selectedItemsedit.delete(prePostionedit);
                        // 클릭한 Item의 position을 저장
                        selectedItemsedit.put(position, true);
                    }
                    // 해당 포지션의 변화를 알림
                    if (prePostionedit != -1) notifyItemChanged(prePostionedit);
                    notifyItemChanged(position);
                    // 클릭된 position 저장
                    prePostionedit = position;

                    break;
                case R.id.deleteOtherBCButton:
                    int OwnerSeq = myItem.OwnerSeq;
                    varForDeleteOtherBC var=new varForDeleteOtherBC(OwnerSeq);
                    BusinessCardService businessCardService = teamebcapp.ebc.Retrofit.retrofit.create(BusinessCardService.class);
                    Call<Map<String,Object>> deleteCall = businessCardService.DeleteOtherBC(var, UserInfo.access_token);
                    deleteCall.enqueue(new Callback<Map<String,Object>>() {
                        @Override
                        public void onResponse(Call<Map<String,Object>> call, Response<Map<String,Object>> response) {

                            try {
                                if (response.isSuccessful()) {
                                    if(Objects.equals(response.body().get("ResultCode"), "200")){
                                        Toast.makeText(context, "명함이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                                        Log.i("ASDF","~~~~~~~~~~~~~~~~~~~~~삭제됨");
                                        mItems.remove(getAdapterPosition());
                                        notifyItemChanged(getAdapterPosition());
                                        notifyItemChanged(getAdapterPosition(),mItems.size());
                                    }
                                    else{
                                        Toast.makeText(context, "request code가 정확하지 않습니다", Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    Toast.makeText(context, "삭제에 실패하였습니다", Toast.LENGTH_SHORT).show();
                                    Log.i("ASDF", "~~~~~~~~~~~~~~~~~~~~~삭제실패~~~~~");
                                }
                            } catch (Exception e) {
                                Log.i("ASDF","~~~~~~~~~~~~~~~~~~~~~오류남~~~~");
                            }
                        }

                        @Override
                        public void onFailure(Call<Map<String,Object>> call, Throwable t) {

                            call.cancel();
                        }
                    });
                    break;

            }
        }

        /**
         * 클릭된 Item의 상태 변경
         *
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

        private void changeVisibilityedit(final boolean isExpanded) {
            // height 값을 dp로 지정해서 넣고싶으면 아래 소스를 이용
            int dpValue = 300;
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

                    getLayout.getLayoutParams().height = value;
                    getLayout.requestLayout();
                    // imageView가 실제로 사라지게하는 부분
                    getLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
                }
            });
            // Animation start
            va.start();
        }

    }
}
