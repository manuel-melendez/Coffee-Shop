package CS213.Team24;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * this class configures the recycler adapter needed for the recyclerView
 * @author Jesse Fisher and Manuel Melendez
 */
public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
    private ArrayList<Donut> donutList;
    private OnDonutClickListener onDonutClickListener;

    /**
     * this method initializes the donut list and donutClickListener
     * @param donutList an array list for storing possible donut flavors
     * @param onDonutClickListener an interface
     */
    public recyclerAdapter(ArrayList<Donut> donutList, OnDonutClickListener onDonutClickListener){
        this.donutList = donutList;
        this.onDonutClickListener = onDonutClickListener;
    }

    /**
     * this class implements the onClickListener
     */
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView donutTxt;
        OnDonutClickListener onDonutClickListener;

        /**
         * this method initializes the onClickListener
         * @param onDonutClickListener the listener made from the interface
         * @param view a view
         */
        public MyViewHolder(final View view, OnDonutClickListener onDonutClickListener){
            super(view);
            this.onDonutClickListener = onDonutClickListener;
            donutTxt = view.findViewById(R.id.donutTextView);

            view.setOnClickListener(this);
        }

        /**
         * this method is called whenever a donut item is clicked
         * @param v a view
         */
        @Override
        public void onClick(View v) {
            onDonutClickListener.onDonutClick(getAdapterPosition());
        }
    }

    /**
     * this function inflates an itemView and initializes a MyViewHolder object
     * @param parent the parent viewGroup
     * @param viewType an integer value
     */
    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.donut_item, parent, false);
        return new MyViewHolder(itemView, onDonutClickListener);
    }

    /**
     * this function retrieves and sets item names
     * @param holder a recyclerView holder
     * @param position position of the clicked item
     */
    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String dName = donutList.get(position).getItemName();
        holder.donutTxt.setText(dName);

    }

    /**
     * this function returns the size of the donut array list
     */
    @Override
    public int getItemCount() {
        return donutList.size();
    }

    /**
     * this is an interface for implementing an onClickListener
     */
    public interface OnDonutClickListener{
        /**
         * this is called whenever a donut item is clicked
         * @param position the position of the clicked item
         */
        void onDonutClick(int position);
    }
}
