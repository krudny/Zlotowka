import { NavigationLinksProps } from "@/interfaces/navigation/NavigationLinksProps";
import { motion } from "framer-motion";

export default function NavigationLinks({
  links,
  isMobile = false,
}: NavigationLinksProps) {
  const linkVariants = {
    hidden: { opacity: 0, x: -20 },
    visible: (index: number) => ({
      opacity: 1,
      x: 0,
      transition: { delay: 0.3 + index * 0.1 },
    }),
  };

  return (
    <div className="flex flex-col flex-1 gap-y-6 my-6">
      {links.map((link, index) => (
        <motion.div
          key={link}
          className={`${isMobile ? "border-l-2" : "border-l-4"} border-neutral-100`}
          variants={linkVariants}
          initial="hidden"
          animate="visible"
          custom={index}
        >
          <p className="ml-6 text-xl hover:cursor-pointer hover:text-neutral-300 transition-colors">
            {link}
          </p>
        </motion.div>
      ))}
    </div>
  );
}
