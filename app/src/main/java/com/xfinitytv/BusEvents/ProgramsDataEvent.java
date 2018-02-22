package com.xfinitytv.BusEvents;

public class ProgramsDataEvent {
        private boolean isTaskSuccessful;

        public boolean isTaskSuccessful() {
            return isTaskSuccessful;
        }

        public void setTaskSuccessful(boolean isTaskSuccessful) {
            this.isTaskSuccessful = isTaskSuccessful;
        }

        public ProgramsDataEvent(boolean isSuccessful) {
            this.isTaskSuccessful = isSuccessful;
        }
}
