package cgeo.geocaching.filter;

import cgeo.geocaching.Geocache;
import cgeo.geocaching.R;

import org.eclipse.jdt.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

abstract class StateFilter extends AbstractFilter {

    protected StateFilter(final int nameResourceId) {
        super(nameResourceId);
    }

    static class StateFoundFilter extends StateFilter {

        public StateFoundFilter() {
            super(R.string.cache_status_found);
        }

        @Override
        public boolean accepts(@NonNull final Geocache cache) {
            return cache.isFound();
        }

    }

    static class StateNotFoundFilter extends StateFilter {

        public StateNotFoundFilter() {
            super(R.string.cache_not_status_found);
        }

        @Override
        public boolean accepts(@NonNull final Geocache cache) {
            return !cache.isFound();
        }

    }

    static class StateArchivedFilter extends StateFilter {
        public StateArchivedFilter() {
            super(R.string.cache_status_archived);
        }

        @Override
        public boolean accepts(@NonNull final Geocache cache) {
            return cache.isArchived();
        }
    }

    static class StateDisabledFilter extends StateFilter {
        public StateDisabledFilter() {
            super(R.string.cache_status_disabled);
        }

        @Override
        public boolean accepts(@NonNull final Geocache cache) {
            return cache.isDisabled();
        }
    }

    static class StatePremiumFilter extends StateFilter {
        public StatePremiumFilter() {
            super(R.string.cache_status_premium);
        }

        @Override
        public boolean accepts(@NonNull final Geocache cache) {
            return cache.isPremiumMembersOnly();
        }
    }

    static class StateNonPremiumFilter extends StateFilter {
        public StateNonPremiumFilter() {
            super(R.string.cache_status_not_premium);
        }

        @Override
        public boolean accepts(@NonNull final Geocache cache) {
            return !cache.isPremiumMembersOnly();
        }
    }

    private static class StateOfflineLogFilter extends StateFilter {
        public StateOfflineLogFilter() {
            super(R.string.cache_status_offline_log);
        }

        @Override
        public boolean accepts(@NonNull final Geocache cache) {
            return cache.isLogOffline();
        }
    }

    static class StateStoredFilter extends StateFilter {
        public StateStoredFilter() {
            super(R.string.cache_status_stored);
        }

        @Override
        public boolean accepts(@NonNull final Geocache cache) {
            return cache.isOffline();
        }
    }

    static class StateNotStoredFilter extends StateFilter {
        public StateNotStoredFilter() {
            super(R.string.cache_status_not_stored);
        }

        @Override
        public boolean accepts(@NonNull final Geocache cache) {
            return !cache.isOffline();
        }
    }

    public static class Factory implements IFilterFactory {

        @Override
        @NonNull
        public List<StateFilter> getFilters() {
            final List<StateFilter> filters = new ArrayList<>(6);
            filters.add(new StateFoundFilter());
            filters.add(new StateNotFoundFilter());
            filters.add(new StateArchivedFilter());
            filters.add(new StateDisabledFilter());
            filters.add(new StatePremiumFilter());
            filters.add(new StateNonPremiumFilter());
            filters.add(new StateOfflineLogFilter());
            filters.add(new StateStoredFilter());
            filters.add(new StateNotStoredFilter());

            Collections.sort(filters, new Comparator<StateFilter>() {

                @Override
                public int compare(final StateFilter filter1, final StateFilter filter2) {
                    return String.CASE_INSENSITIVE_ORDER.compare(filter1.getName(), filter2.getName());
                }
            });

            return filters;
        }

    }

}
