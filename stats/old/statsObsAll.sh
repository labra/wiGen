for obs in 1 5 10 50 100 500 1000
do
 ./wigen -c 1 -g 1 --dataSets 1 -o $obs --shex --shacl --time --no-single >> obsObsAll 
done