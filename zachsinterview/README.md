To interview:

1) pull latest code from master (will have all relevant files deleted)

git checkout master
git pull

2) create a new branch using git:

git checkout -b interview-[persons_name]

3) cherry pick and push relevant commit (497717f87a8606c9703ced9414823a4271c3f00f)

git cherry-pick 497717f87a8606c9703ced9414823a4271c3f00f
git push --set-upstream origin interview-[persons_name]

4) open a PR in github
