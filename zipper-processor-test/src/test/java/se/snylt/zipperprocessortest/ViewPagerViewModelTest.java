package se.snylt.zipperprocessortest;

import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

import se.snylt.zipper.viewbinder.Binder;
import se.snylt.zipper.viewbinder.ViewBinder;
import se.snylt.zipperprocessortest.utils.TestViewBinderUtils;

import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static se.snylt.zipperprocessortest.utils.TestViewBinderUtils.verifyViewBinder;

public class ViewPagerViewModelTest extends TestBase {

    @Override
    protected void testBind(List<ViewBinder> binder, Object viewHolder) {
        verifyViewBinder(
                binder,
                viewHolder,
                new ViewPagerViewModel(),
                getViewMocker(),
                new TestViewBinderUtils.VerifyPostBind<ViewPagerViewModel>() {
                    @Override
                    public void onPostBind(View mockedView, ViewPagerViewModel viewModel, int viewId) {
                        verify((ViewPagerAdapter) ((ViewPager) mockedView).getAdapter()).setItems(same(viewModel.items));
                    }
                }
        );
    }

    @Override
    protected Object getViewHolder() {
        return new ViewPagerViewModel_ViewHolder();
    }

    @Override
    protected Binder getBinder() {
        return new ViewPagerViewModel_ViewBinder().createBinder();
    }

    public TestViewBinderUtils.ViewMocker getViewMocker() {
        return new TestViewBinderUtils.ViewMocker() {
            @Override
            public View mockView() {
                ViewPagerAdapter adapter = mock(ViewPagerAdapter.class);
                ViewPager viewPager = mock(ViewPager.class);
                when(viewPager.getAdapter()).thenReturn(adapter);
                return viewPager;
            }
        };
    }
}