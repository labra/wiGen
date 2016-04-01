values="1 5 10 50 100 500 1000" 
#wiGenRunner="\basura\wigen-0.0.1\wigen-0.0.1\bin\wigen.bat"
wiGenRunner=./wigen

for value in $values
do
outName="outSlices"
params="--countries 1 --dataSets 1 --slices $value --observations 1 --computations 1 --Indicators 1 --Organizations 1 --shex --time"
paramsSingle="$params --no-scopeNodes"
paramsBad="$params --badSlices 1"
$wiGenRunner $params >> $outName 
$wiGenRunner $paramsSingle >> $outName
$wiGenRunner $paramsBad >> $outName
done


