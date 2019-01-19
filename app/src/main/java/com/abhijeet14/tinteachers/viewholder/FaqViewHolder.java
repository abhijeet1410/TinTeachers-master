package com.abhijeet14.tinteachers.viewholder;

import android.support.annotation.NonNull;
import android.support.design.card.MaterialCardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.abhijeet14.tinteachers.R;

public class FaqViewHolder extends RecyclerView.ViewHolder {
    private TextView question, answer;
    private MaterialCardView card;

    public FaqViewHolder(@NonNull View itemView) {
        super(itemView);
        question = itemView.findViewById(R.id.faq_row_question);
        answer = itemView.findViewById(R.id.faq_row_answer);
        card = itemView.findViewById(R.id.main_faq_row);
    }

    public void setQuestion(String question) {
        this.question.setText(question);
    }

    public void setAnswer(String answer) {
        this.answer.setText(answer);
    }

    public MaterialCardView getCard() {
        return card;
    }
}
