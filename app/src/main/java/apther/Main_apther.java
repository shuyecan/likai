package apther;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Been.Mainbeen;
import com.white.progressview.CircleProgressView;

import java.util.List;

import sy.yuanlixiaoduzi.com.R;

/**
 * Created by Administrator on 2018/10/30.
 */

public class Main_apther extends RecyclerView.Adapter<Main_apther.ViewHolder> {
    private Context context;
    private List<Mainbeen> Datalist;
    public Main_apther(List<Mainbeen> data_list) {
        Datalist = data_list;
    }
    @NonNull
    @Override
    public Main_apther.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context==null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.main_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Main_apther.ViewHolder holder, int position) {
        Mainbeen dataBean = Datalist.get(position);
        holder.main_tv1.setText("第 "+dataBean.getTian()+" 天");
        holder.circleProgressView.setProgress(dataBean.getJingdu());
    }

    @Override
    public int getItemCount() {
        return Datalist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView main_tv1;
        CardView item_layth;
        CircleProgressView circleProgressView;
        public ViewHolder(View itemView) {
            super(itemView);
            item_layth = (CardView) itemView;
            main_tv1 = itemView.findViewById(R.id.main_item_tv1);
            circleProgressView = itemView.findViewById(R.id.circle_progress_normal);
        }
    }
}
