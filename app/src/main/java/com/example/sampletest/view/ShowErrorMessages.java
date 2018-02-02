package com.example.sampletest.view;

/**
 * Created by emb-sadabir on 2/2/18.
 */

public interface ShowErrorMessages {

    void showError(int resId, Object... args);

    void showError(String errorMessage);

}
