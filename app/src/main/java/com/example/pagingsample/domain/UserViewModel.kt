package com.example.pagingsample.domain

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Config
import androidx.paging.toLiveData
import com.example.pagingsample.usecases.database.UserDatabase
import com.example.pagingsample.usecases.database.entity.User
import com.example.pagingsample.utils.ioThread

/**
 * A simple ViewModel that provides a paged list of Users.
 */
class UserViewModel(app: Application) : AndroidViewModel(app) {
    val dao = UserDatabase.get(app).userDao()
    /**
     * We use -ktx Kotlin extension functions here, otherwise you would use LivePagedListBuilder(),
     * and PagedList.Config.Builder()
     */
    val allUsers = dao.allUsersByName().toLiveData(
        Config(
            /**
             * A good page size is a value that fills at least a screen worth of content on a large
             * device so the User is unlikely to see a null item.
             * You can play with this constant to observe the paging behavior.
             * <p>
             * It's possible to vary this with list device size, but often unnecessary, unless a
             * user scrolling on a large device is expected to scroll through items more quickly
             * than a small device, such as when the large device uses a grid layout of items.
             */
            pageSize = 5,
            /**
             * If placeholders are enabled, PagedList will report the full size but some items might
             * be null in onBind method (PagedListAdapter triggers a rebind when data is loaded).
             * <p>
             * If placeholders are disabled, onBind will never receive null but as more pages are
             * loaded, the scrollbars will jitter as new pages are loaded. You should probably
             * disable scrollbars if you disable placeholders.
             */
            enablePlaceholders = true,
            /**
             * Maximum number of items a PagedList should hold in memory at once.
             * <p>
             * This number triggers the PagedList to start dropping distant pages as more are loaded.
             */
//            maxSize = 30,
            initialLoadSizeHint = 20
        )
    )

    fun insert(text: CharSequence) = ioThread {
        dao.insert(User(id = 0, name = text.toString()))
    }

    fun remove(user: User) = ioThread {
        dao.delete(user)
    }
}