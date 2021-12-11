package com.example.clc.clc_server.dao;

import com.example.clc.clc_server.dto.CLCReviewDTO;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

public class CLCDAO {
    private Firestore firestore = com.google.firebase.cloud.FirestoreClient.getFirestore();
        
    public void WriteReview(CLCReviewDTO dto) {
        ApiFuture<DocumentReference> result = firestore.collection("Review").add(dto);
        System.out.println("WriteReview : " + result);
        return;
    }

    public void DeleteReview(String docId) {
        ApiFuture<WriteResult> result = firestore.document("Review/" + docId).delete();
        System.out.println("DeleteReview : " + result);
        return;
    }

    public void UpdateReview(String docId, CLCReviewDTO dto){
        firestore.document("Review/" + docId)
        .update("content", dto.getContent(), "title", dto.getTitle());
        return;
    }
}
