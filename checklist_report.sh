echo "Number Lines:\t" `find . -name "*java" | xargs wc -l | tail -1 | cut -f2`
echo "Classes:\t" `grep -R "class " --include=*.java . | grep "{" | wc -l`
echo "Abstract:\t" `grep -R "abstract " --include=*.java . | grep "class " | grep "{" | wc -l`
echo "Subclasses:\t" `grep -R " extends " --include=*.java . | wc -l`
echo "Interfaces:\t" `grep -R "interface " --include=*.java . | grep "{" | wc -l`
echo "Interfaces Used:" `grep -R " implements " --include=*.java . | wc -l`
echo ""
echo ""
echo "Global instance variables"
grep -R "public|protected" --include=*.java . | grep -v "(" | grep -v "class\|interface\|final" | grep "static" | grep -v "{"
echo ""
echo ""
echo "Non-private instance variables"
grep -R "public\|protected" --include=*.java . | grep -v "(" | grep -v "class\|interface\|final" | grep -v "static" | grep -v "{"
echo ""
echo ""
echo "Use of Array List"
grep -R "public .*ArrayList<.*>" --include=*.java .
echo ""
echo ""
echo "Use of Hash Map"
grep -R "public .*HashMap<.*,.*>" --include=*.java . 
echo ""
echo ""
echo "Use of Hash Set"
grep -R "public .*HashSet<.*>" --include=*.java . 
echo ""
echo ""
echo "Longest Methods"
./lineCountJava.perl `find . -name "*.java"` | sort -nr | head -12 | tail -10
echo ""
echo ""
echo "Bad Exception Handling"
grep -R "printStackTrace" --include=*.java .
