package itboom.com.swap.auth;

import androidx.fragment.app.Fragment;

public interface AuthRouting {
    void navigateTo(Fragment fragment, boolean addToBackStack);
}
