package com.example.appbookroom.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appbookroom.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class CalendarFragment extends Fragment {

    private final String TAG = "TAG_DAY_VIEW";
    private View view;

    private Calendar day;
    private SimpleDateFormat timeFormat;

    private DayView dayView;
    private HorizontalCalendar horizontalCalendar;
    public CalendarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,Bundle savedInstanceState) {
        // Tạo lịch từ ngày hôm nay
        day = Calendar.getInstance();
        day.set(Calendar.HOUR_OF_DAY, 0);
        day.set(Calendar.MINUTE, 0);
        day.set(Calendar.SECOND, 0);
        day.set(Calendar.MILLISECOND, 0);

        // Điền mục nhập của ngày hôm nay với danh sách các sự kiện mẫu

        timeFormat = new SimpleDateFormat("HH:mm");

        view = inflater.inflate(R.layout.fragment_calendar,container,false);

        dayView = view.findViewById(R.id.sample_day);

        // Tăng chế độ xem nhãn cho mỗi giờ, chế độ xem ngày hiển thị
        Calendar hour = (Calendar) day.clone();
        List<View> hourLabelViews = new ArrayList<>();
        for (int i = dayView.getStartHour(); i <= dayView.getEndHour(); i++) {
            hour.set(Calendar.HOUR_OF_DAY, i);
            TextView hourLabelView = (TextView) getLayoutInflater().inflate(R.layout.hour_label, dayView, false);
            hourLabelView.setText(timeFormat.format(hour.getTime()));
            hourLabelViews.add(hourLabelView);
        }
        dayView.setHourLabelViews(hourLabelViews);
        onEventsChange();
        onSetHorizontalCalendarView();

        return view;
    }

    private void onEventsChange() {
        List<View> eventViews = null;
        List<DayView.EventTimeRange> eventTimeRanges = null;
        List<Event> events = new ArrayList<>();
        //TODO Call API
        events.add(new Event("1","1",9,15,90));
        events.add(new Event("1","1",9,15,30));
        events.add(new Event("1","1",13,15,30));

        if (events != null) {
            // Sắp xếp các sự kiện theo thời gian bắt đầu để bố cục diễn ra theo đúng thứ tự
            Collections.sort(events, new Comparator<Event>() {
                @Override
                public int compare(Event o1, Event o2) {
                    Log.e(TAG, "compare: ");
                    return o1.hour < o2.hour ? -1 : (o1.hour == o2.hour ? (o1.minute < o2.minute ? -1 : (o1.minute == o2.minute ? 0 : 1)) : 1);
                }
            });
            eventViews = new ArrayList<>();
            eventTimeRanges = new ArrayList<>();

            // Xác nhận lại tất cả các chế độ xem sự kiện hiện có để có thể sử dụng lại chúng nếu cần, quy trình này
            // có thể hữu ích nếu chế độ xem trong ngày của bạn được lưu trữ trong chế độ xem tái chế chẳng hạn
            List<View> recycled = dayView.removeEventViews();
            int remaining = recycled != null ? recycled.size() : 0;

            for (final Event event : events) {
                // Cố gắng tái chế một chế độ xem sự kiện hiện có nếu còn đủ, nếu không sẽ tăng một chế độ xem mới
                View eventView = remaining > 0 ? recycled.get(--remaining) : getLayoutInflater().inflate(R.layout.event, dayView, false);

                ((TextView) eventView.findViewById(R.id.event_title)).setText(event.title);
                ((TextView) eventView.findViewById(R.id.event_location)).setText(event.location);

                // Khi một sự kiện được nhấp, hãy bắt đầu một sự kiện nháp mới và hiển thị hộp thoại chỉnh sửa sự kiện
                eventView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO Event listener
                    }
                });

                eventViews.add(eventView);

                // The day view needs the event time ranges in the start minute/end minute format,
                // so calculate those here
                int startMinute = 60 * event.hour + event.minute;
                int endMinute = startMinute + event.duration;
                eventTimeRanges.add(new DayView.EventTimeRange(startMinute, endMinute));
            }
        }

        // Update the day view with the new events
        dayView.setEventViews(eventViews, eventTimeRanges);
    }

    private void onSetHorizontalCalendarView(){
        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();

        endDate.add(Calendar.MONTH, 3);
        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(view, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(6)
                .configure()
                .textSize(18f, 18f, 18f)
                .showTopText(false)
                .end()
                .build();
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                // TODO Add Event
            }

        });
        horizontalCalendar.goToday(true);
    }

    /**
     * A data class used to represent an event on the calendar.
     */
    private static class Event {
        @Nullable
        private final String title;
        @Nullable
        private final String location;
        private final int hour;
        private final int minute;
        private final int duration;

        private Event(@Nullable String title, @Nullable String location, int hour, int minute, int duration) {
            this.title = title;
            this.location = location;
            this.hour = hour;
            this.minute = minute;
            this.duration = duration;
        }
    }

}
