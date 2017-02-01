package com.timejh.runtimepermission;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by tokijh on 2017. 2. 1..
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {

    ArrayList<ContactData> datas;
    Context context;

    public RecyclerViewAdapter(Context context) {
        datas = new ArrayList<>();
        this.context = context;
    }

    public void add(ContactData contactData) {
        datas.add(contactData);
        this.notifyDataSetChanged();
    }

    public void set(ArrayList<ContactData> datas) {
        this.datas = datas;
        this.notifyDataSetChanged();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final ContactData user = datas.get(position);
        holder.tv_name.setText(user.getName());

        if (user.getNum().size() > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            for (String number : user.getNum()) {
                stringBuffer.append(number + '\n');
            }
            String strnumbers = stringBuffer.substring(0, stringBuffer.length() - 1).toString();
            holder.tv_num.setText(strnumbers);
        } else {
            holder.tv_num.setText("");
        }

        holder.ib_dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getNum().size() <= 0) {
                    Toast.makeText(context, "전화번호가 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                final ArrayAdapter<String> dialogAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, user.getNum());
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                alertBuilder.setIcon(android.R.drawable.ic_menu_call);
                alertBuilder.setTitle("전화번호를 선택하세요.");
                alertBuilder.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertBuilder.setAdapter(dialogAdapter,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String number = dialogAdapter.getItem(id);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    if (context.checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
                                        context.startActivity(intent);
                                    }
                                } else {
                                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
                                    context.startActivity(intent);
                                }
                            }
                        });
                alertBuilder.show();
            }
        });

        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        holder.cardView.setAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        TextView tv_num;
        ImageButton ib_dial;

        CardView cardView;

        public CustomViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_num = (TextView) itemView.findViewById(R.id.tv_num);
            ib_dial = (ImageButton) itemView.findViewById(R.id.ib_dial);

            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }
}