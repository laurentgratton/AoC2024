re='^[0-9]+$'

if [ $# -gr 0 ] && [[ $1 =~ $re ]]; then
  DAY=$1
else
  DAY="1"
fi

echo "Building and running day $DAY"

docker build --tag "aoc-d$DAY" --build-arg DAY=$DAY .
docker run --rm aoc-d$DAY
docker rmi aoc-d$DAY