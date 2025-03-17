import "./globals.css";
import Sidebar from "@/components/Sidebar";

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
      <html lang="pl">
      <head>
        <link rel="preconnect" href="https://fonts.googleapis.com"/>
        <link rel="preconnect" href="https://fonts.gstatic.com"/>
        <link
            href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=optional&family=Raleway:ital,wght@0,100..900;1,100..900&display=optional"
            rel="stylesheet"/>
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200&icon_names=settings"/>
      </head>
      <body className="flex min-h-screen ">
      <div className="w-96 h-screen sticky left-0 top-0">
        <Sidebar/>
      </div>
      <div className="w-full">
      {children}
      </div>
      </body>
      </html>
  );
}
