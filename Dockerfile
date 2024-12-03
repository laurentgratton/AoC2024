FROM clojure:tools-deps
ARG DAY
ENV DAYVAL=$DAY
COPY . ./
RUN clj -P
CMD clj -X day$DAYVAL/run