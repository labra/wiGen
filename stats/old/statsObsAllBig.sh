for obs in 1000 5000 10000 50000 100000 500000 1000000
do
 ./wigen -c 81 -g 5 --dataSets 5 -o $obs --shex --time --no-single >> obsObsAllBig 
done